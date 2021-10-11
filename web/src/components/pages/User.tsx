import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import QRCode from 'react-qr-code';
import { getUserURL } from '../../util/user';
import { Typography } from '@mui/material';

type Params = {
  uuid: string;
};

const User: FC = () => {
  const { uuid } = useParams<Params>();
  const url = getUserURL(uuid);
  return (
    <div>
      <div className="flex justify-center p-10">
        <QRCode value={url} />
      </div>
      <Typography variant="h6" className="text-center">
        入退出時にこの画面を見せてください
      </Typography>
    </div>
  );
};

export default User;
