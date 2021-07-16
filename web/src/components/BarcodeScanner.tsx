import { FC, RefObject, useCallback, useLayoutEffect } from 'react';
import Quagga, {
  QuaggaJSResultCallbackFunction,
  QuaggaJSResultObject,
} from '@ericblade/quagga2';

type Props = {
  onDetected: (result: QuaggaJSResultObject) => void;
  scannerRef: RefObject<HTMLDivElement>;
  onScannerReady?: () => void;
  cameraId?: string;
  facingMode?: string;
  constraints?: ConstraintsType;
  locator?: LocatorType;
  numOfWorkers?: number;
  decoders?: string[];
  locate?: boolean;
};

type ConstraintsType = {
  width: number;
  height: number;
};

type LocatorType = {
  patchSize: string;
  halfSample: boolean;
};

const defaultConstraints: ConstraintsType = {
  width: 640,
  height: 480,
};

const defaultLocator = {
  patchSize: 'medium',
  halfSample: true,
};

const defaultDecoders = ['ean_reader'];

const BarcodeScanner: FC<Props> = ({
  onDetected,
  scannerRef,
  onScannerReady,
  cameraId,
  facingMode,
  constraints = defaultConstraints,
  locator = defaultLocator,
  numOfWorkers = navigator.hardwareConcurrency || 0,
  decoders = defaultDecoders,
  locate = true,
}) => {
  const errorCheck: QuaggaJSResultCallbackFunction = useCallback(
    (result: QuaggaJSResultObject) => {
      if (!onDetected) {
        return;
      }
      const error = getMedianOfCodeErrors(result);
      // if Quagga is at least 75% certain that it read correctly, then accept the code.
      if (error < 0.25) {
        onDetected(result);
      }
    },
    [onDetected]
  );

  const handleProcessed = (result: QuaggaJSResultObject) => {
    const drawingCtx = Quagga.canvas.ctx.overlay;
    const drawingCanvas = Quagga.canvas.dom.overlay;
    drawingCtx.font = '24px Arial';
    drawingCtx.fillStyle = 'green';

    if (result) {
      if (result.boxes) {
        drawingCtx.clearRect(
          0,
          0,
          parseInt(drawingCanvas.getAttribute('width') as string),
          parseInt(drawingCanvas.getAttribute('height') as string)
        );
        result.boxes
          .filter((box) => box !== result.box)
          .forEach((box) => {
            Quagga.ImageDebug.drawPath(box, { x: 0, y: 1 }, drawingCtx, {
              color: 'purple',
              lineWidth: 2,
            });
          });
      }
      if (result.box) {
        Quagga.ImageDebug.drawPath(result.box, { x: 0, y: 1 }, drawingCtx, {
          color: 'blue',
          lineWidth: 2,
        });
      }
      if (result.codeResult && result.codeResult.code) {
        drawingCtx.font = '24px Arial';
        drawingCtx.fillText(result.codeResult.code, 10, 20);
      }
    }
  };

  useLayoutEffect(() => {
    Quagga.init(
      {
        inputStream: {
          type: 'LiveStream',
          constraints: {
            ...constraints,
            ...(cameraId && { deviceId: cameraId }),
            ...(!cameraId && { facingMode }),
          },
          target: scannerRef.current || undefined,
        },
        locator,
        numOfWorkers,
        decoder: { readers: decoders },
        locate,
      },
      (error) => {
        Quagga.onProcessed(handleProcessed);
        if (error) {
          return console.log('Error starting Quagga:', error);
        }
        if (scannerRef && scannerRef.current) {
          Quagga.start();
          if (onScannerReady) {
            onScannerReady();
          }
        }
      }
    );
    Quagga.onDetected(errorCheck);
    return () => {
      Quagga.offDetected(errorCheck);
      Quagga.offProcessed(handleProcessed);
      Quagga.stop();
    };
  }, [
    cameraId,
    constraints,
    decoders,
    errorCheck,
    facingMode,
    locate,
    locator,
    numOfWorkers,
    onDetected,
    onScannerReady,
    scannerRef,
  ]);

  return null;
};

const getMedian = (errors: number[]): number => {
  errors.sort((a, b) => a - b);
  const half = Math.floor(errors.length / 2);
  if (errors.length % 2 === 1) {
    return errors[half];
  }
  return (errors[half - 1] + errors[half]) / 2;
};

const getMedianOfCodeErrors = (result: QuaggaJSResultObject): number => {
  const errors = result.codeResult.decodedCodes
    .map((item) => item.error)
    .filter((error): error is number => typeof error === 'number');
  return getMedian(errors);
};

export default BarcodeScanner;
