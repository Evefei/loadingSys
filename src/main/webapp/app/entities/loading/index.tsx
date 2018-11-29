import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Loading from './loading';
import LoadingDetail from './loading-detail';
import LoadingUpdate from './loading-update';
import LoadingDeleteDialog from './loading-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoadingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoadingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoadingDetail} />
      <ErrorBoundaryRoute path={match.url} component={Loading} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoadingDeleteDialog} />
  </>
);

export default Routes;
