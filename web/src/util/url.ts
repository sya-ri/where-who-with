/**
 * 現在のURLを取得する
 */
export const getCurrentUrl = (): string => {
  const { protocol, host } = location;
  return `${protocol}//${host}`;
};
