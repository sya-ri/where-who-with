import BlockIcon from '@mui/icons-material/Block';
import QrCodeScannerIcon from '@mui/icons-material/QrCodeScanner';
import { Typography } from '@mui/material';
import React, { FC } from 'react';
import QRCode from 'react-qr-code';
import { useLocation, useParams } from 'react-router-dom';
import * as Paths from '../../Paths';
import DocsSection from '../parts/DocsSection';
import DocsSubSection from '../parts/DocsSubSection';
import DocsTemplate from '../templates/DocsTemplate';

interface Params {
  uuid: string;
}

const AreaDocs: FC = () => {
  const { uuid } = useParams<Params>();
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const name = params.get('name');
  const url = Paths.Area(uuid);
  return (
    <DocsTemplate>
      {name && (
        <Typography variant="body2" className="text-right text-gray-600">
          {name}
        </Typography>
      )}
      <DocsSection title="1. ページにアクセスする">
        <QRCode value={url} size={96} />
        <Typography className="pt-2">QRコードからアクセスする。</Typography>
      </DocsSection>
      <DocsSection title="2. ユーザーのQRコードを読み取る">
        <DocsSubSection
          title="QRコードを持っている場合"
          icon={QrCodeScannerIcon}
        >
          ウェブサイト内のカメラから、QRコードを読み取る。
        </DocsSubSection>
        <DocsSubSection title="QRコードを持っていない場合" icon={BlockIcon}>
          入室を断り、近くの受付または本部へ誘導する。
        </DocsSubSection>
      </DocsSection>
      <DocsSection title="3. 入室を記録する">
        QRコードの下に書かれている文字と表示された文字が同じであることを確認して、「入室」を押す。
      </DocsSection>
      <DocsSection title="4. 退室を記録する">
        入室と同じ手順で「退室」を押す。間違えて「入室」を押さないように注意する。
      </DocsSection>
    </DocsTemplate>
  );
};

export default AreaDocs;
