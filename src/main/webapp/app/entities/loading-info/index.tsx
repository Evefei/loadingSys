import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LoadingInfo from './loading-info';
import LoadingInfoDetail from './loading-info-detail';
import LoadingInfoUpdate from './loading-info-update';
import LoadingInfoDeleteDialog from './loading-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoadingInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoadingInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoadingInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={LoadingInfo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoadingInfoDeleteDialog} />
  </>
);

export default Routes;
