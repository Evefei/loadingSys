import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './loading-info.reducer';
import { ILoadingInfo } from 'app/shared/model/loading-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoadingInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class LoadingInfo extends React.Component<ILoadingInfoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { loadingInfoList, match } = this.props;
    return (
      <div>
        <h2 id="loading-info-heading">
          <Translate contentKey="loadingSysApp.loadingInfo.home.title">Loading Infos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="loadingSysApp.loadingInfo.home.createLabel">Create new Loading Info</Translate>
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
                  <Translate contentKey="loadingSysApp.loadingInfo.step">Step</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.materialCode">Material Code</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.colorCode">Color Code</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.layoutType">Layout Type</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.quantity">Quantity</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.row">Row</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.column">Column</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.layer">Layer</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.lengthPosition">Length Position</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.widthPosition">Width Position</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.heightPosition">Height Position</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.length">Length</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.width">Width</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.height">Height</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.loadingInfo.loadingId">Loading Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {loadingInfoList.map((loadingInfo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${loadingInfo.id}`} color="link" size="sm">
                      {loadingInfo.id}
                    </Button>
                  </td>
                  <td>{loadingInfo.step}</td>
                  <td>{loadingInfo.materialCode}</td>
                  <td>{loadingInfo.colorCode}</td>
                  <td>{loadingInfo.layoutType}</td>
                  <td>{loadingInfo.quantity}</td>
                  <td>{loadingInfo.row}</td>
                  <td>{loadingInfo.column}</td>
                  <td>{loadingInfo.layer}</td>
                  <td>{loadingInfo.lengthPosition}</td>
                  <td>{loadingInfo.widthPosition}</td>
                  <td>{loadingInfo.heightPosition}</td>
                  <td>{loadingInfo.length}</td>
                  <td>{loadingInfo.width}</td>
                  <td>{loadingInfo.height}</td>
                  <td>{loadingInfo.loadingId ? <Link to={`loading/${loadingInfo.loadingId.id}`}>{loadingInfo.loadingId.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${loadingInfo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${loadingInfo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${loadingInfo.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ loadingInfo }: IRootState) => ({
  loadingInfoList: loadingInfo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoadingInfo);
