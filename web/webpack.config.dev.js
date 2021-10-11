/* eslint-disable @typescript-eslint/no-var-requires */
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');
const { DefinePlugin } = require('webpack');

module.exports = merge(baseConfig, {
  mode: 'development',
  devtool: 'source-map',
  plugins: [
    new DefinePlugin({
      'process.env.API_URL': '"http://localhost:8080"',
    }),
  ],
  devServer: {
    historyApiFallback: true,
    port: 80,
  },
});
