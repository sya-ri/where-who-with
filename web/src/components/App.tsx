import React, { FC, lazy, Suspense } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import * as Paths from '../Paths';
import { AlertProvider } from '../context/AlertContext';
const Area = lazy(() => import('./pages/Area'));
const AreaDocs = lazy(() => import('./pages/AreaDocs'));
const Desk = lazy(() => import('./pages/Desk'));
const DeskView = lazy(() => import('./pages/DeskView'));
const DeskDocs = lazy(() => import('./pages/DeskDocs'));
const NotFound = lazy(() => import('./pages/NotFound'));
const User = lazy(() => import('./pages/User'));

const App: FC = () => (
  <AlertProvider>
    <div className="h-full">
      <Suspense fallback={<></>}>
        <BrowserRouter>
          <Switch>
            <Route exact path={Paths.Area(':uuid')} component={Area} />
            <Route exact path={Paths.AreaDocs(':uuid')} component={AreaDocs} />
            <Route exact path={Paths.Desk(':uuid')} component={Desk} />
            <Route exact path={Paths.DeskDocs(':uuid')} component={DeskDocs} />
            <Route exact path={Paths.DeskView(':uuid')} component={DeskView} />
            <Route exact path={Paths.User(':uuid')} component={User} />
            <Route exact component={NotFound} />
          </Switch>
        </BrowserRouter>
      </Suspense>
    </div>
  </AlertProvider>
);

export default App;
