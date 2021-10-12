import { Typography } from '@mui/material';
import React, { FC } from 'react';
import QRCode from 'react-qr-code';
import { useParams } from 'react-router-dom';
import { getUserURL } from '../../util/user';

interface Params {
  uuid: string;
}

const User: FC = () => {
  const { uuid } = useParams<Params>();
  const url = getUserURL(uuid);
  return (
    <div>
      <Typography variant="h5" className="text-center pb-2">
        ユーザー情報
      </Typography>
      <div className="p-4 pb-2">
        <div className="flex justify-center pb-2">
          <QRCode value={url} />
        </div>
        <Typography variant="body2" className="text-gray-600 text-center">
          {uuid}
        </Typography>
      </div>
      <Typography variant="h6" className="text-center pt-4">
        入退出時にこの画面を見せてください
      </Typography>
    </div>
  );
};

export default User;
