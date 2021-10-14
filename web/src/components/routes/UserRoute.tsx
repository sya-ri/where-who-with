import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import UserPage from '../pages/UserPage';

interface Params {
  uuid: string;
}

const UserRoute: FC = () => {
  const { uuid } = useParams<Params>();
  return <UserPage uuid={uuid} />;
};

export default UserRoute;
