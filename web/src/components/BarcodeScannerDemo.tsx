import React, { FC, useRef, useState } from 'react';
import BarcodeScanner from './BarcodeScanner';

const BarcodeScannerDemo: FC = () => {
  const [scanning, setScanning] = useState(false);
  const scannerRef = useRef<HTMLDivElement>(null);

  return (
    <div>
      <button onClick={() => setScanning(!scanning)}>
        {scanning ? 'Stop' : 'Start'}
      </button>
      <div ref={scannerRef} style={{ position: 'relative' }}>
        <canvas
          className="drawingBuffer"
          style={{
            position: 'absolute',
            top: '0px',
          }}
          width="640"
          height="480"
        />
        {scanning ? (
          <BarcodeScanner
            scannerRef={scannerRef}
            onDetected={(result) =>
              console.log(
                `${result.codeResult.code} [${result.codeResult.format}]`
              )
            }
          />
        ) : null}
      </div>
    </div>
  );
};

export default BarcodeScannerDemo;
