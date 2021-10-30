import { Button, ButtonProps } from '@mui/material';
import html2canvas from 'html2canvas';
import React, { FC } from 'react';

interface Props {
  fileName: string;
  elementId: string;
  ButtonProps?: ButtonProps;
}

export const ExportAsImageButton: FC<Props> = ({
  children,
  fileName,
  elementId,
  ButtonProps,
}) => (
  <Button
    onClick={() => {
      const target = document.getElementById(elementId);
      if (target) {
        onClickExport(target, fileName);
      }
    }}
    {...ButtonProps}
  >
    {children}
  </Button>
);

const saveAsImage = (uri: string, fileName: string) => {
  const downloadLink = document.createElement('a');
  downloadLink.href = uri;
  downloadLink.download = fileName;
  document.body.appendChild(downloadLink);
  downloadLink.click();
  document.body.removeChild(downloadLink);
};

const onClickExport = (target: HTMLElement, fileName: string) => {
  html2canvas(target).then((canvas) => {
    const targetImgUri = canvas.toDataURL('img/png');
    saveAsImage(targetImgUri, fileName);
  });
};
