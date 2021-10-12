import { Button, TextField, Typography } from '@mui/material';
import React, { FC, useState } from 'react';
import { useParams } from 'react-router-dom';
import * as Pages from '../../Pages';
import * as api from '../../api/method';

type Params = {
  uuid: string;
};

const Desk: FC = () => {
  const { uuid } = useParams<Params>();
  const [checkUserId, setCheckUserId] = useState<number | null>();
  return (
    <div>
      <Typography variant="h5" className="text-center pb-2">
        受付画面
      </Typography>
      <div className="border-b border-gray-400 py-4">
        <div className="text-center mx-auto">
          <Button
            variant="contained"
            onClick={() => {
              api
                .postUserCreate({ desk_uuid: uuid })
                .then((response) => {
                  const { user_id, user_uuid } = response.data;
                  const params = `?id=${user_id}&uuid=${user_uuid}`;
                  window.location.href = Pages.DeskView(uuid) + params;
                })
                .catch((reason) => console.log(reason));
            }}
            className="h-14 w-full"
          >
            新しくユーザーを作成する
          </Button>
        </div>
      </div>
      <div className="pt-4">
        <div className="flex justify-between">
          <TextField
            variant="outlined"
            type="number"
            value={checkUserId}
            label="ユーザーID"
            onChange={(event) => {
              const value = parseInt(event.target.value);
              if (0 < value) {
                setCheckUserId(value);
              } else {
                setCheckUserId(null);
              }
              event.preventDefault();
            }}
          />
          <Button
            variant="contained"
            disabled={!(checkUserId && 0 < checkUserId)}
            onClick={() => {
              if (checkUserId) {
                api
                  .postUserCheck({ desk_uuid: uuid, user_id: checkUserId })
                  .then((response) => {
                    const { user_uuid } = response.data;
                    const params = `?id=${checkUserId}&uuid=${user_uuid}`;
                    window.location.href = Pages.DeskView(uuid) + params;
                  })
                  .catch((reason) => {
                    console.error(reason);
                    setCheckUserId(null);
                  });
              }
            }}
          >
            検索
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Desk;
