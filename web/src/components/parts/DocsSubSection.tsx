import { SvgIcon, Typography } from '@mui/material';
import React, { FC } from 'react';

interface Props {
  title: string;
  icon: typeof SvgIcon;
}

const DocsSubSection: FC<Props> = ({ title, icon, children }) => (
  <div className="py-1">
    <Typography variant="h6">
      {React.createElement(icon, { className: 'mr-2' }, null)}
      {title}
    </Typography>
    <Typography className="pt-2 pl-2">{children}</Typography>
  </div>
);

export default DocsSubSection;
