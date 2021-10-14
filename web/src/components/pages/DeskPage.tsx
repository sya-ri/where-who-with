import { Button, TextField, Typography } from '@mui/material';
import React, { FC, useState } from 'react';
import * as Paths from '../../Paths';
import * as api from '../../api/method';
import { useAlert } from '../../context/AlertContext';

interface Props {
  uuid: string;
}

const DeskPage: FC<Props> = (props) => {
  const { uuid } = props;
  const alert = useAlert();
  const [checkUserId, setCheckUserId] = useState<number | null>();
  const search = () => {
    if (checkUserId) {
      api
        .postUserCheck({ desk_uuid: uuid, user_id: checkUserId })
        .then((response) => {
          const { user_uuid } = response.data;
          const params = `?id=${checkUserId}&uuid=${user_uuid}`;
          window.location.href = Paths.DeskView(uuid) + params;
        })
        .catch((reason) => {
          alert.error('検索に失敗しました');
          console.error(reason);
          setCheckUserId(null);
        });
    }
  };
  return (
    <div className="flex justify-center items-center h-full">
      <div>
        <Typography variant="h5" className="pb-2 text-center">
          受付画面
        </Typography>
        <div className="py-4 border-b border-gray-400">
          <div className="mx-auto text-center">
            <Button
              variant="contained"
              onClick={() => {
                api
                  .postUserCreate({ desk_uuid: uuid })
                  .then((response) => {
                    const { user_id, user_uuid } = response.data;
                    const params = `?id=${user_id}&uuid=${user_uuid}`;
                    window.location.href = Paths.DeskView(uuid) + params;
                  })
                  .catch((reason) => {
                    alert.error('ユーザー作成に失敗しました');
                    console.error(reason);
                  });
              }}
              className="w-full h-14"
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

export default DeskPage;
