import { Button, TextField, Typography } from '@mui/material';
import React, { FC } from 'react';
import QRCode from 'react-qr-code';
import { useLocation, useParams } from 'react-router-dom';
import * as Pages from '../../Pages';
import { useAlert } from '../../context/AlertContext';
import { getUserURL } from '../../util/user';

type Params = {
  uuid: string;
};

const DeskView: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const alert = useAlert();
  const params = new URLSearchParams(location.search);
  const userUuid = params.get('uuid') || '';
  const userId = params.get('id');
  const userPageUrl = getUserURL(userUuid);
  return (
    <div>
      <Typography variant="h5" className="text-center pb-2">
        受付確認画面
      </Typography>
      <div className="p-4 pb-2">
        <div className="flex justify-center pb-2">
          <QRCode value={userPageUrl} />
        </div>
        <Typography variant="body2" className="text-gray-600 text-center">
          {userUuid}
        </Typography>
      </div>
      <div className="text-center w-5/6 mx-auto pt-4">
        <TextField
          label="ID"
          type="number"
          defaultValue={userId}
          InputProps={{
            readOnly: true,
          }}
          size="small"
          className="w-1/2"
        />
        <div className="flex justify-between pt-6">
          <Button
            variant="outlined"
            onClick={() => {
              window.location.href = Pages.Desk(uuid);
            }}
            className="w-5/12"
          >
            戻る
          </Button>
          <Button
            variant="contained"
            className="w-5/12"
            onClick={() => alert.warning('未実装です')} // TODO 印刷処理
          >
            印刷
          </Button>
        </div>
      </div>
    </div>
  );
};

export default DeskView;
