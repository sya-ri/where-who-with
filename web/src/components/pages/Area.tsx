import React, { FC } from 'react';
import { useParams } from 'react-router-dom';

type Params = {
  uuid: string;
};

const Area: FC = () => {
  const { uuid } = useParams<Params>();
  return <>Area / {uuid}</>;
};

export default Area;
