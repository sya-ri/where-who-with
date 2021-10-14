import { Button, TextField, Typography } from '@mui/material';
import React, { FC, useState } from 'react';
import { useParams } from 'react-router-dom';
import * as Pages from '../../Pages';
import * as api from '../../api/method';
import { useAlert } from '../../context/AlertContext';

interface Params {
  uuid: string;
}

const Desk: FC = () => {
  const { uuid } = useParams<Params>();
  const alert = useAlert();
  const [checkUserId, setCheckUserId] = useState<number | null>();
  const search = () => {
    if (checkUserId) {
      api
        .postUserCheck({ desk_uuid: uuid, user_id: checkUserId })
        .then((response) => {
          const { user_uuid } = response.data;
          const params = `?id=${checkUserId}&uuid=${user_uuid}`;
          window.location.href = Pages.DeskView(uuid) + params;
        })
        .catch((reason) => {
          alert.error('検索に失敗しました');
          console.error(reason);
          setCheckUserId(null);
        });
    }
  };
  return (
    <div className="h-full flex justify-center items-center">
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
                  .catch((reason) => {
                    alert.error('ユーザー作成に失敗しました');
                    console.error(reason);
                  });
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
              onKeyDown={(e) => {
                if (e.key === 'Enter') {
                  search();
                }
              }}
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
              disabled={!checkUserId}
              onClick={search}
            >
              検索
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Desk;
