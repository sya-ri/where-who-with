import { Button, Typography } from '@mui/material';
import React, { FC, useState } from 'react';
import QrReader from 'react-qr-reader';
import { useParams } from 'react-router-dom';
import * as api from '../../api/method';
import { useAlert } from '../../context/AlertContext';

interface Params {
  uuid: string;
}

const Area: FC = () => {
  const { uuid } = useParams<Params>();
  const alert = useAlert();
  const [userUuid, setUserUuid] = useState<string | null>();
  return (
    <div className="flex justify-center items-center h-full">
      <div>
        <Typography variant="h5" className="pb-2 text-center">
          入退室記録
        </Typography>
        <div className="pt-4 m-auto w-64">
          <QrReader
            onScan={(data) => {
              if (data) {
                setUserUuid(data.substring(data.lastIndexOf('/') + 1));
              }
            }}
            onError={(error) => {
              alert.error('読み取りに問題が発生しました');
              console.error(error);
            }}
          />
        </div>
        <div>
          <Typography
            variant="body2"
            className="pt-2 pb-4 text-center text-gray-600"
          >
            {userUuid || 'QRコードをスキャンしてください'}
          </Typography>
          <div className="flex justify-between">
            <Button
              variant="contained"
              color="success"
              disabled={!userUuid}
              onClick={() => {
                if (userUuid) {
                  api
                    .postLogJoin({ area_uuid: uuid, user_uuid: userUuid })
                    .then(() => {
                      alert.success('入室を記録しました');
                      setUserUuid(null);
                    })
                    .catch((reason) => {
                      alert.error('入室記録に失敗しました');
                      console.error(reason);
                      setUserUuid(null);
                    });
                }
              }}
              className="w-5/12"
            >
              入室
            </Button>
            <Button
              variant="contained"
              color="error"
              disabled={!userUuid}
              onClick={() => {
                if (userUuid) {
                  api
                    .postLogLeave({ area_uuid: uuid, user_uuid: userUuid })
                    .then(() => {
                      alert.success('退室を記録しました');
                      setUserUuid(null);
                    })
                    .catch((reason) => {
                      alert.error('退室記録に失敗しました');
                      console.error(reason);
                      setUserUuid(null);
                    });
                }
              }}
              className="w-5/12"
            >
              退室
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Area;
