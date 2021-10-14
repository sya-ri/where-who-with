import { Button, TextField, Typography } from '@mui/material';
import { Location } from 'history';
import React, { FC, useRef } from 'react';
import QRCode from 'react-qr-code';
import { useLocation, useParams } from 'react-router-dom';
import ReactToPrint, { useReactToPrint } from 'react-to-print';
import * as Pages from '../../Pages';
import { getUserURL } from '../../util/user';

interface Params {
  uuid: string;
}

const getUserInfoFromSearch = (
  location: Location
): { userId: string; userUuid: string } => {
  const params = new URLSearchParams(location.search);
  const userUuid = params.get('uuid') || '';
  const userId = params.get('id') || '';
  return { userId, userUuid };
};

const DeskView: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const { userUuid, userId } = getUserInfoFromSearch(location);
  const userPageUrl = getUserURL(userUuid);
  const componentRef = useRef<HTMLDivElement>(null);
  const handlePrint = useReactToPrint({
    content: () => componentRef.current,
  });
  return (
    <div className="h-full flex justify-center items-center">
      <div>
        <Typography variant="h5" className="text-center pb-4">
          受付確認画面
        </Typography>
        <div ref={componentRef} className="py-2">
          <div className="flex justify-center pb-2">
            <a href={userPageUrl}>
              <QRCode value={userPageUrl} />
            </a>
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
            <ReactToPrint
              trigger={() => (
                <Button
                  variant="contained"
                  className="w-5/12"
                  onClick={handlePrint}
                >
                  印刷
                </Button>
              )}
              content={() => componentRef.current}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default DeskView;
