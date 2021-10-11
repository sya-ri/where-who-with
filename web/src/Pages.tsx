export const Area = (uuid: string): string => `/area/${uuid}`;
export const Desk = (uuid: string): string => `/desk/${uuid}`;
export const DeskView = (uuid: string): string => `${Desk(uuid)}/view`;
export const User = (uuid: string): string => `/user/${uuid}`;
