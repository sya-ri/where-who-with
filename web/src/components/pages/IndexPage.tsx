import BarChartIcon from '@mui/icons-material/BarChart';
import ContactPageIcon from '@mui/icons-material/ContactPage';
import LocalOfferIcon from '@mui/icons-material/LocalOffer';
import QrCodeIcon from '@mui/icons-material/QrCode';
import { TextField } from '@mui/material';
import React, { FC } from 'react';
import DocsSection from '../parts/DocsSection';
import DocsSubSection from '../parts/DocsSubSection';
import DocsTemplate from '../templates/DocsTemplate';

const IndexPage: FC = () => (
  <DocsTemplate>
    <div className="text-right">
      <TextField
        label="ID"
        size="small"
        InputLabelProps={{ shrink: true }}
        InputProps={{ readOnly: true }}
      />
    </div>
    <DocsSection title="どうすればいいの？">
      各企画場所でQRコードをスキャンします。企画に入るとき・出るときにQRコードを見せられるように準備しておいてください。
    </DocsSection>
    <DocsSection title="考え方">
      <DocsSubSection title="どのように判断するか" icon={BarChartIcon}>
        QRコードを各場所で読み取ることで、入退室の記録を取ります。&quot;どこ&quot;
        に &quot;いつ&quot;
        いたのかを収集することで、接触した人を調べることが可能です。
      </DocsSubSection>
      <DocsSubSection title="個人情報との紐付け" icon={LocalOfferIcon}>
        接触確認アプリのユーザー作成時に表示されるIDと書類に記載するナンバーのみが対応してるので、接触確認アプリから個人情報が漏洩する危険性はありません。
      </DocsSubSection>
    </DocsSection>
    <DocsSection title="助けて！">
      <DocsSubSection
        title="QRコードのページを閉じてしまった"
        icon={ContactPageIcon}
      >
        ページを閉じてしまったら、履歴を開いてみてください。そのページが残っているはずです。
        残っていなければ &quot;QRコードを失くしてしまった&quot; をご覧ください。
      </DocsSubSection>
      <DocsSubSection title="QRコードを失くしてしまった" icon={QrCodeIcon}>
        受付 もしくは 本部 に行ってください。ID があれば再発行が可能です。
      </DocsSubSection>
    </DocsSection>
  </DocsTemplate>
);

export default IndexPage;
