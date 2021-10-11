/**
 * ユーザーページにアクセスするURLを取得する
 */
export const getUserURL = (uuid: string): string => {
  const { protocol, host } = location;
  return `${protocol}//${host}/user/${uuid}`;
};
