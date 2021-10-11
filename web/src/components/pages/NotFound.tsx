import React, { FC } from 'react';
import { Typography } from '@mui/material';

const NotFound: FC = () => (
  <div className="text-center">
    <Typography variant="h2">404</Typography>
    <Typography variant="h5">ページが見つかりませんでした</Typography>
  </div>
);

export default NotFound;
