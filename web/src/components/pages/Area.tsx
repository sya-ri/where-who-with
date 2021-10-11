import React, { FC, useState } from 'react';
import { useParams } from 'react-router-dom';
import QrReader from 'react-qr-reader';
import { Button, Typography } from '@mui/material';
import * as api from '../../api/method';

type Params = {
  uuid: string;
};

const Area: FC = () => {
  const { uuid } = useParams<Params>();
  const [userUuid, setUserUuid] = useState<string | null>();
  return (
    <div>
      <Typography variant="h5" className="text-center pb-2">
        入退室記録
      </Typography>
      <div className="m-auto w-64 pt-4">
        <QrReader
          onScan={(data) => {
            if (data) {
              setUserUuid(data.substring(data.lastIndexOf('/') + 1));
            }
          }}
          onError={(error) => {
            console.error(error);
          }}
        />
      </div>
      <div>
        <Typography
          variant="body2"
          className="text-gray-600 text-center pt-2 pb-4"
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
                    setUserUuid(null);
                  })
                  .catch((error) => {
                    console.error(error);
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
                    setUserUuid(null);
                  })
                  .catch((error) => {
                    console.error(error);
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
  );
};

export default Area;
