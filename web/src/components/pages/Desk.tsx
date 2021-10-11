import React, { FC, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Button, TextField, Typography } from '@mui/material';
import * as api from '../../api/method';
import * as Pages from '../../Pages';

type Params = {
  uuid: string;
};

const Desk: FC = () => {
  const { uuid } = useParams<Params>();
  const [checkUserId, setCheckUserId] = useState<number | null>();
  return (
    <div className="text-center">
      <Typography variant="h3" className="p-4">
        受付
      </Typography>
      <div className="mb-2">
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
        >
          ユーザー作成
        </Button>
      </div>
      <div>
        <TextField
          variant="outlined"
          type="number"
          value={checkUserId}
          onChange={(event) => {
            const value = parseInt(event.target.value);
            if (value) {
              setCheckUserId(value);
            }
          }}
        />
        <Button
          variant="contained"
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
          表示
        </Button>
      </div>
    </div>
  );
};

export default Desk;
