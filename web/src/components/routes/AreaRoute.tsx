import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import AreaPage from '../pages/AreaPage';

interface Params {
  uuid: string;
}

const AreaRoute: FC = () => {
  const { uuid } = useParams<Params>();
  return <AreaPage uuid={uuid} />;
};

export default AreaRoute;
