import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './loading.reducer';
import { ILoading } from 'app/shared/model/loading.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoadingProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Loading extends React.Component<ILoadingProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { loadingList, match } = this.props;
    return (
      <div>
        <h2 id="loading-heading">
          <Translate contentKey="loadingSysApp.loading.home.title">Loadings</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="loadingSysApp.loading.home.createLabel">Create new Loading</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.loadingNo">Loading No</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.volumeUtilization">Volume Utilization</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.weightUtilization">Weight Utilization</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.materialQuantity">Material Quantity</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.materialVolume">Material Volume</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.materialWeight">Material Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.lengthRemain">Length Remain</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.widthRemain">Width Remain</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loading.heightRemain">Height Remain</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {loadingList.map((loading, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${loading.id}`} color="link" size="sm">
                      {loading.id}
                    </Button>
                  </td>
                  <td>{loading.loadingNo}</td>
                  <td>{loading.volumeUtilization}</td>
                  <td>{loading.weightUtilization}</td>
                  <td>{loading.materialQuantity}</td>
                  <td>{loading.materialVolume}</td>
                  <td>{loading.materialWeight}</td>
                  <td>{loading.lengthRemain}</td>
                  <td>{loading.widthRemain}</td>
                  <td>{loading.heightRemain}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${loading.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${loading.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${loading.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ loading }: IRootState) => ({
  loadingList: loading.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Loading);
