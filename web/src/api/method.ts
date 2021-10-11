import axios from 'axios';
import {
  LogRequest,
  LogResponse,
  UserCheckRequest,
  UserCheckResponse,
  UserCreateRequest,
  UserCreateResponse,
} from './model';

const url = process.env.API_URL;

export const postUserCheck = (
  request: UserCheckRequest
): Promise<UserCheckResponse> => {
  return axios.post<UserCreateRequest, UserCheckResponse>(
    `${url}/user/check`,
    request
  );
};

export const postUserCreate = (
  request: UserCreateRequest
): Promise<UserCreateResponse> => {
  return axios.post<UserCreateRequest, UserCreateResponse>(
    `${url}/user/create`,
    request
  );
};

export const postLogJoin = (request: LogRequest): Promise<LogResponse> => {
  return axios.post<LogRequest, LogResponse>(`${url}/log/join`, request);
};

export const postLogLeave = (request: LogRequest): Promise<LogResponse> => {
  return axios.post<LogRequest, LogResponse>(`${url}/log/leave`, request);
};
