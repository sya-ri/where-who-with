import React, { FC } from 'react';
import { useParams } from 'react-router-dom';

type Params = {
  uuid: string;
};

const User: FC = () => {
  const { uuid } = useParams<Params>();
  return <>User / {uuid}</>;
};

export default User;
