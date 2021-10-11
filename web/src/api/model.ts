export interface UserCheckRequest {
  desk_uuid: string;
  user_id: number;
}

export interface UserCheckResponse {
  user_uuid: string;
}

export interface UserCreateRequest {
  desk_uuid: string;
}

export interface UserCreateResponse {
  user_id: number;
  user_uuid: string;
}

export interface LogRequest {
  user_uuid: string;
  area_uuid: string;
}

export interface LogResponse {
  date: string;
}
