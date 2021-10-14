import { Typography } from '@mui/material';
import React, { FC } from 'react';

interface Props {
  title: string;
}

const DocsSection: FC<Props> = ({ title, children }) => (
  <div className="py-2">
    <Typography variant="h5">{title}</Typography>
    <div className="pt-2 pl-4">{children}</div>
  </div>
);

export default DocsSection;
