import React, { FC } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import QRCode from 'react-qr-code';
import { getUserURL } from '../../util/user';

type Params = {
  uuid: string;
};

const DeskView: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const userUuid = params.get('uuid') || '';
  const userId = params.get('id');
  const userPageUrl = getUserURL(userUuid);
  console.log(userPageUrl);
  return (
    <div>
      <QRCode value={userPageUrl} />
    </div>
  );
};

export default DeskView;
