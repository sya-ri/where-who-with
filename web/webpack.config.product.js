/* eslint-disable @typescript-eslint/no-var-requires */
const { DefinePlugin } = require('webpack');
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');

process.env.NODE_ENV = 'production';

module.exports = merge(baseConfig, {
  mode: 'production',
  optimization: {
    minimize: true,
  },
  plugins: [
    new DefinePlugin({
      'process.env.API_URL': '"https://w3server.kokasai.com"',
    }),
  ],
});
