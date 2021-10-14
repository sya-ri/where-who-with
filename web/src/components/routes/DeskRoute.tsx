import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import DeskPage from '../pages/DeskPage';

interface Params {
  uuid: string;
}

const DeskRoute: FC = () => {
  const { uuid } = useParams<Params>();
  return <DeskPage uuid={uuid} />;
};

export default DeskRoute;
