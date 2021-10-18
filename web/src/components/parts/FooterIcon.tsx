import { SvgIconComponent } from '@mui/icons-material';
import { Tooltip } from '@mui/material';
import React, { FC } from 'react';

interface Props {
  href: string;
  icon: SvgIconComponent;
  tooltip: string;
}

const FooterIcon: FC<Props> = (props) => {
  const { href, icon, tooltip } = props;
  return (
    <div className="absolute right-0 bottom-0 p-4">
      <Tooltip title={tooltip}>
        <a href={href} target="_blank" rel="noreferrer">
          {React.createElement(icon, { fontSize: 'large' }, null)}
        </a>
      </Tooltip>
    </div>
  );
};

export default FooterIcon;
