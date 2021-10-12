import Alert from '@mui/material/Alert';
import Snackbar from '@mui/material/Snackbar';
import React, { FC, useState } from 'react';
import createContext from '../util/createContext';

type AlertContextType = {
  /**
   * info アラートを表示する
   * @param message メッセージ
   * @param displayTime 閉じるまでの時間 / 初期値: 5000ms
   */
  info: (message: string, displayTime?: number) => void;

  /**
   * success アラートを表示する
   * @param message メッセージ
   * @param displayTime 閉じるまでの時間 / 初期値: 5000ms
   */
  success: (message: string, displayTime?: number) => void;

  /**
   * warning アラートを表示する
   * @param message メッセージ
   * @param displayTime 閉じるまでの時間 / 初期値: 5000ms
   */
  warning: (message: string, displayTime?: number) => void;

  /**
   * error アラートを表示する
   * @param message メッセージ
   * @param displayTime 閉じるまでの時間 / 初期値: 5000ms
   */
  error: (message: string, displayTime?: number) => void;

  close: () => void;
};

type AlertSeverity = 'info' | 'success' | 'warning' | 'error';

type AlertState = {
  open: boolean;
  severity: AlertSeverity;
  message: string;
  closeTimer: number;
};

const [useAlert, SetAlertProvider] = createContext<AlertContextType>();

const useAlertContext = (): { state: AlertState; type: AlertContextType } => {
  const [alertState, setAlertState] = useState<AlertState>({
    closeTimer: 0,
    message: '',
    open: false,
    severity: 'success',
  });

  const close = () => {
    setAlertState((lastState) => ({ ...lastState, open: false }));
  };

  const open = (
    severity: AlertSeverity,
    message: string,
    displayTime = 5000
  ) => {
    if (alertState.closeTimer) {
      clearTimeout(alertState.closeTimer);
    }
    let closeTimer = 0;
    if (displayTime) {
      closeTimer = window.setTimeout(close, displayTime);
    }
    setAlertState({
      closeTimer,
      message,
      open: true,
      severity,
    });
  };

  const info = (message: string, displayTime = 5000) =>
    open('info', message, displayTime);
  const success = (message: string, displayTime = 5000) =>
    open('success', message, displayTime);
  const warning = (message: string, displayTime = 5000) =>
    open('warning', message, displayTime);
  const error = (message: string, displayTime = 5000) =>
    open('error', message, displayTime);
  return {
    state: alertState,
    type: {
      close,
      error,
      info,
      success,
      warning,
    },
  };
};

export const AlertProvider: FC = (props) => {
  const { children } = props;
  const { state, type } = useAlertContext();
  return (
    <SetAlertProvider value={type}>
      {children}
      <Snackbar
        open={state.open}
        anchorOrigin={{
          horizontal: 'center',
          vertical: 'bottom',
        }}
      >
        <Alert onClose={type.close} severity={state.severity}>
          {state.message}
        </Alert>
      </Snackbar>
    </SetAlertProvider>
  );
};

export { useAlert };
