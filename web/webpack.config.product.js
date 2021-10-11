/* eslint-disable @typescript-eslint/no-var-requires */
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');
const { DefinePlugin } = require('webpack');

module.exports = merge(baseConfig, {
  mode: 'production',
  plugins: [
    new DefinePlugin({
      'process.env.API_URL': '"https://w3server.kokasai.com"',
    }),
  ],
  optimization: {
    minimize: true,
  },
});
