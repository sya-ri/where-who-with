import { Typography } from '@mui/material';
import React, { FC } from 'react';
import QRCode from 'react-qr-code';
import * as Paths from '../../Paths';
import { getCurrentUrl } from '../../util/url';

interface Props {
  uuid: string;
}

const UserPage: FC<Props> = (props) => {
  const { uuid } = props;
  const url = getCurrentUrl() + Paths.User(uuid);
  return (
    <div className="flex justify-center items-center h-full">
      <div>
        <Typography variant="h5" className="pb-2 text-center">
          ユーザー情報
        </Typography>
        <div className="p-4 pb-2">
          <div className="flex justify-center pb-2">
            <QRCode value={url} />
          </div>
          <Typography variant="body2" className="text-center text-gray-600">
            {uuid}
          </Typography>
        </div>
        <Typography variant="h6" className="pt-4 text-center">
          入退室時にこの画面を見せてください
        </Typography>
      </div>
    </div>
  );
};

export default UserPage;
