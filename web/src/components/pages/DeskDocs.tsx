import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PhoneIphoneIcon from '@mui/icons-material/PhoneIphone';
import PrintIcon from '@mui/icons-material/Print';
import SearchIcon from '@mui/icons-material/Search';
import { Typography } from '@mui/material';
import React, { FC } from 'react';
import QRCode from 'react-qr-code';
import { useLocation, useParams } from 'react-router-dom';
import * as Pages from '../../Pages';
import DocsSection from '../parts/DocsSection';
import DocsSubSection from '../parts/DocsSubSection';
import DocsTemplate from '../templates/DocsTemplate';

interface Params {
  uuid: string;
}

const DeskDocs: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const name = params.get('name');
  const url = Pages.Desk(uuid);
  return (
    <DocsTemplate>
      {name && (
        <Typography variant="body2" className="text-gray-600 text-right">
          {name}
        </Typography>
      )}
      <DocsSection title="1. ページにアクセスする">
        <QRCode value={url} size={96} />
        <Typography className="pt-2">QRコードからアクセスする。</Typography>
      </DocsSection>
      <DocsSection title="2. 受付確認画面を表示する">
        <DocsSubSection title="ユーザーの追加" icon={PersonAddIcon}>
          「新しくユーザーを作成する」を押す。
        </DocsSubSection>
        <DocsSubSection title="ユーザーの検索" icon={SearchIcon}>
          「ユーザーID」を入力し「検索」を押す。ユーザーを作成した時と同じ画面が表示される。
        </DocsSubSection>
      </DocsSection>
      <DocsSection title="3. ユーザーにQRコードを渡す">
        <DocsSubSection title="携帯を使う" icon={PhoneIphoneIcon}>
          ユーザーにQRコードを読み取ってもらう。カメラアプリから読み取ることができる。
        </DocsSubSection>
        <DocsSubSection title="紙に印刷する" icon={PrintIcon}>
          「印刷」を押して、紙を渡す。
        </DocsSubSection>
      </DocsSection>
      <DocsSection title="4. 書類にIDを記入する">
        <Typography>
          個人を特定できる書類とユーザーに渡す書類に表示されたIDを記入する。
        </Typography>
      </DocsSection>
    </DocsTemplate>
  );
};

export default DeskDocs;
