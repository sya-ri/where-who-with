import React, { FC } from 'react';
import { useParams } from 'react-router-dom';

type Params = {
  uuid: string;
};

const DeskView: FC = () => {
  const { uuid } = useParams<Params>();
  return <>DeskView / {uuid}</>;
};

export default DeskView;
