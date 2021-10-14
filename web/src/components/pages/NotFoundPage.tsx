import { Typography } from '@mui/material';
import React, { FC } from 'react';

const NotFoundPage: FC = () => (
  <div className="flex justify-center items-center h-full">
    <div className="text-center">
      <Typography variant="h2">404</Typography>
      <Typography variant="h5">ページが見つかりませんでした</Typography>
    </div>
  </div>
);

export default NotFoundPage;
