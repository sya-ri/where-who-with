import { Button, TextField, Typography } from '@mui/material';
import React, { FC, useRef } from 'react';
import QRCode from 'react-qr-code';
import ReactToPrint, { useReactToPrint } from 'react-to-print';
import * as Paths from '../../Paths';
import { getCurrentUrl } from '../../util/url';

interface Props {
  uuid: string;
  userUuid: string;
  userId: string;
}

const DeskViewPage: FC<Props> = (props) => {
  const { uuid, userUuid, userId } = props;
  const url = getCurrentUrl() + Paths.User(userUuid);
  const componentRef = useRef<HTMLDivElement>(null);
  const handlePrint = useReactToPrint({
    content: () => componentRef.current,
  });
  return (
    <div className="flex justify-center items-center h-full">
      <div>
        <Typography variant="h5" className="pb-4 text-center">
          受付確認画面
        </Typography>
        <div ref={componentRef} className="py-2">
          <div className="flex justify-center pb-2">
            <a href={url}>
              <QRCode value={url} />
            </a>
          </div>
          <Typography variant="body2" className="text-center text-gray-600">
            {userUuid}
          </Typography>
        </div>
        <div className="pt-4 mx-auto w-5/6 text-center">
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
                window.location.href = Paths.Desk(uuid);
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

export default DeskViewPage;
