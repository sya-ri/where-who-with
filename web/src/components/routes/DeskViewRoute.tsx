import React, { FC } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import DeskViewPage from '../pages/DeskViewPage';

interface Params {
  uuid: string;
}

const DeskViewRoute: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const userUuid = params.get('uuid') || '';
  const userId = params.get('id') || '';
  return <DeskViewPage uuid={uuid} userUuid={userUuid} userId={userId} />;
};

export default DeskViewRoute;
