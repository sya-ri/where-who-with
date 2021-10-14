import React, { FC, lazy, Suspense } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import * as Pages from '../Pages';
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
    <div className="app h-full">
      <Suspense fallback={<></>}>
        <BrowserRouter>
          <Switch>
            <Route exact path={Pages.Area(':uuid')} component={Area} />
            <Route exact path={Pages.AreaDocs(':uuid')} component={AreaDocs} />
            <Route exact path={Pages.Desk(':uuid')} component={Desk} />
            <Route exact path={Pages.DeskDocs(':uuid')} component={DeskDocs} />
            <Route exact path={Pages.DeskView(':uuid')} component={DeskView} />
            <Route exact path={Pages.User(':uuid')} component={User} />
            <Route exact component={NotFound} />
          </Switch>
        </BrowserRouter>
      </Suspense>
    </div>
  </AlertProvider>
);

export default App;
