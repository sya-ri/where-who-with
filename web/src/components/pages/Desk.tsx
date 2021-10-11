import React, { FC } from 'react';
import { useParams } from 'react-router-dom';

type Params = {
  uuid: string;
};

const Desk: FC = () => {
  const { uuid } = useParams<Params>();
  return <>Desk / {uuid}</>;
};

export default Desk;
