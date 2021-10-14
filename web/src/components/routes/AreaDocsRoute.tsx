import React, { FC } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import AreaDocsPage from '../pages/AreaDocsPage';

interface Params {
  uuid: string;
}

const AreaDocsRoute: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const name = params.get('name');
  return <AreaDocsPage name={name} uuid={uuid} />;
};

export default AreaDocsRoute;
