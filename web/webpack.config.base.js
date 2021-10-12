/* eslint-disable @typescript-eslint/no-var-requires */
const path = require('path');
const Autoprefixer = require('autoprefixer');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlMinimizerPlugin = require('html-minimizer-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TailwindCss = require('tailwindcss');

module.exports = {
  entry: './src/index.tsx',
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: 'ts-loader',
      },
      {
        test: /\.css$/i,
        use: [
          MiniCssExtractPlugin.loader,
          {
            loader: 'css-loader',
            options: {
              sourceMap: true,
            },
          },
          {
            loader: 'postcss-loader',
            options: {
              postcssOptions: {
                plugins: [TailwindCss, Autoprefixer],
              },
            },
          },
        ],
      },
    ],
  },
  optimization: {
    minimizer: [new HtmlMinimizerPlugin()],
  },
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
  },
  plugins: [
    new HtmlWebpackPlugin({
      inject: false,
      template: './html/index.html',
    }),
    new HtmlWebpackPlugin({
      filename: '404.html',
      inject: false,
      template: './html/404.html',
    }),
    new CopyWebpackPlugin({
      patterns: [{ from: 'static' }],
    }),
    new MiniCssExtractPlugin({
      filename: 'index.css',
    }),
  ],
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.json'],
  },
  target: ['web', 'es5'],
};
