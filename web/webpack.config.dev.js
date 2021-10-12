/* eslint-disable @typescript-eslint/no-var-requires */
const { DefinePlugin } = require('webpack');
const { merge } = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');

module.exports = merge(baseConfig, {
  devServer: {
    historyApiFallback: true,
    port: 80,
  },
  devtool: 'source-map',
  mode: 'development',
  plugins: [
    new DefinePlugin({
      'process.env.API_URL': '"http://localhost:8080"',
    }),
  ],
});
