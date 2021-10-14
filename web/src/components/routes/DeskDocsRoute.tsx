import React, { FC } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import DeskDocsPage from '../pages/DeskDocsPage';

interface Params {
  uuid: string;
}

const DeskDocsRoute: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const name = params.get('name');
  return <DeskDocsPage name={name} uuid={uuid} />;
};

export default DeskDocsRoute;
