import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loading.reducer';
import { ILoading } from 'app/shared/model/loading.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoadingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoadingDetail extends React.Component<ILoadingDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loadingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="loadingSysApp.loading.detail.title">Loading</Translate> [<b>{loadingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="loadingNo">
                <Translate contentKey="loadingSysApp.loading.loadingNo">Loading No</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.loadingNo}</dd>
            <dt>
              <span id="volumeUtilization">
                <Translate contentKey="loadingSysApp.loading.volumeUtilization">Volume Utilization</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.volumeUtilization}</dd>
            <dt>
              <span id="weightUtilization">
                <Translate contentKey="loadingSysApp.loading.weightUtilization">Weight Utilization</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.weightUtilization}</dd>
            <dt>
              <span id="materialQuantity">
                <Translate contentKey="loadingSysApp.loading.materialQuantity">Material Quantity</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.materialQuantity}</dd>
            <dt>
              <span id="materialVolume">
                <Translate contentKey="loadingSysApp.loading.materialVolume">Material Volume</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.materialVolume}</dd>
            <dt>
              <span id="materialWeight">
                <Translate contentKey="loadingSysApp.loading.materialWeight">Material Weight</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.materialWeight}</dd>
            <dt>
              <span id="lengthRemain">
                <Translate contentKey="loadingSysApp.loading.lengthRemain">Length Remain</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.lengthRemain}</dd>
            <dt>
              <span id="widthRemain">
                <Translate contentKey="loadingSysApp.loading.widthRemain">Width Remain</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.widthRemain}</dd>
            <dt>
              <span id="heightRemain">
                <Translate contentKey="loadingSysApp.loading.heightRemain">Height Remain</Translate>
              </span>
            </dt>
            <dd>{loadingEntity.heightRemain}</dd>
          </dl>
          <Button tag={Link} to="/entity/loading" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/loading/${loadingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ loading }: IRootState) => ({
  loadingEntity: loading.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoadingDetail);
