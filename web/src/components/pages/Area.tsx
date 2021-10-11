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
      <div>
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
      <div className="text-center">
        <Typography>{userUuid || 'QRコードをスキャンしてください'}</Typography>
        <div>
          <Button
            variant="contained"
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
          >
            入室
          </Button>
          <Button
            variant="contained"
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
          >
            退室
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Area;
