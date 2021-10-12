import axios, { AxiosResponse } from 'axios';
import {
  LogRequest,
  LogResponse,
  UserCheckRequest,
  UserCheckResponse,
  UserCreateRequest,
  UserCreateResponse,
} from './model';

/**
 * - webpack.config.dev.js
 * - webpack.config.product.js
 *
 * で定義されます
 */
const url = process.env.API_URL;

export const postUserCheck = (
  request: UserCheckRequest
): Promise<AxiosResponse<UserCheckResponse>> => {
  return axios.post<UserCreateRequest, AxiosResponse<UserCheckResponse>>(
    `${url}/user/check`,
    request
  );
};

export const postUserCreate = (
  request: UserCreateRequest
): Promise<AxiosResponse<UserCreateResponse>> => {
  return axios.post<UserCreateRequest, AxiosResponse<UserCreateResponse>>(
    `${url}/user/create`,
    request
  );
};

export const postLogJoin = (
  request: LogRequest
): Promise<AxiosResponse<LogResponse>> => {
  return axios.post<LogRequest, AxiosResponse<LogResponse>>(
    `${url}/log/join`,
    request
  );
};

export const postLogLeave = (
  request: LogRequest
): Promise<AxiosResponse<LogResponse>> => {
  return axios.post<LogRequest, AxiosResponse<LogResponse>>(
    `${url}/log/leave`,
    request
  );
};
