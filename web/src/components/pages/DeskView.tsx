import React, { FC } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import QRCode from 'react-qr-code';
import { getUserURL } from '../../util/user';
import { Button, TextField } from '@mui/material';
import * as Pages from '../../Pages';

type Params = {
  uuid: string;
};

const DeskView: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const userUuid = params.get('uuid') || '';
  const userId = params.get('id');
  const userPageUrl = getUserURL(userUuid);
  console.log(userPageUrl);
  return (
    <div>
      <div className="flex justify-center p-10">
        <QRCode value={userPageUrl} />
      </div>
      <div className="text-center">
        <div>
          <TextField
            label="ID"
            type="number"
            defaultValue={userId}
            InputProps={{
              readOnly: true,
            }}
          />
          <Button variant="contained">印刷</Button>
        </div>
        <Button
          variant="outlined"
          onClick={() => {
            window.location.href = Pages.Desk(uuid);
          }}
        >
          戻る
        </Button>
      </div>
    </div>
  );
};

export default DeskView;
