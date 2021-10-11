import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import QRCode from 'react-qr-code';
import { getUserURL } from '../../util/user';

type Params = {
  uuid: string;
};

const User: FC = () => {
  const { uuid } = useParams<Params>();
  const url = getUserURL(uuid);
  return (
    <div>
      <QRCode value={url} />
    </div>
  );
};

export default User;
