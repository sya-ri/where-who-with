/* eslint-disable @typescript-eslint/no-var-requires */
const path = require('path');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlMinimizerPlugin = require('html-minimizer-webpack-plugin');

module.exports = {
    entry: './src/index.tsx',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'main.js',
    },
    target: ['web', 'es5'],
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.json'],
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
            },
        ],
    },
    plugins: [
        new CopyWebpackPlugin({
            patterns: [{ from: 'static' }],
        })
    ],
    optimization: {
        minimizer: [new HtmlMinimizerPlugin()],
    },
};
